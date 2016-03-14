#!/usr/bin/perl

use strict;

my $saturated = 0;

#for saturated analysis:
my $from_very_first_message = 1;
my $print_all_times = 0;# or 1
my $formatted_all_data = 0;
my $formatted_all_sends = 0;

#for non-saturated analysis:
if (0 == $saturated) {
$from_very_first_message = 0;
$print_all_times = 0;
$formatted_all_data = 0;
$formatted_all_sends = 0;
}



my @all_delays;
my @all_sends;
my $file_number = 0;
my $max_which_recv = 0;

sub to_msec {
	(my $hour0, my $min0, my $sec0, my $msec0) = $_[0] =~ /(\d+):(\d+):(\d+),(\d+)/;
	return (($hour0*60+$min0)*60+$sec0)*1000+$msec0;
}

sub difftime {
	my $msec1 = to_msec($_[0]);
	my $msec2 = to_msec($_[1]);
	my $ret = $msec1 - $msec2;
	if ($ret > 0) {return $ret;}
	return (-$ret);
}

sub analysis_round {
	my $which_recv = $_[0];
	my $first_message_publish = $_[1];
	my $last_message_publish = $_[2];
	my $first_message_receive = $_[3];
	my $last_message_receive = $_[4];
	my $print_legenda = $_[5];

	if (1 == $saturated) {
	if (0!=$which_recv and !($first_message_publish eq "")) {
		my $delaypermsg = difftime($first_message_publish, $last_message_receive)/$which_recv;
		my $a = to_msec($first_message_publish);
		my $b = difftime($first_message_publish, $last_message_publish);
		my $c = difftime($first_message_publish, $first_message_receive);
		my $d = difftime($first_message_publish, $last_message_receive);
			print "$which_recv, $a, $b, $c, $d, $delaypermsg\n";
		if (0 != $print_legenda) {
			print "which_recv, first_message_publish, last_message_publish, first_message_recv, last_message_recv, delaypermsg\n";
		}
	}
	}

	if (0 == $saturated) {
	if (!($first_message_publish eq "")) {
		my $sendperiod = difftime($first_message_publish, $last_message_publish)/$which_recv;
		my $sendrate = $which_recv/difftime($first_message_publish, $last_message_publish);
		my $delaysum = 0;
		for (my $i=0;$i<$which_recv;$i++) {
			my $delay1 = $all_delays[$file_number][$i];
			$delaysum += $delay1;
		}
		my $meandelay = $delaysum / $which_recv;

		my $a = to_msec($first_message_publish);
		my $b = difftime($first_message_publish, $last_message_publish);
		my $c = difftime($first_message_publish, $first_message_receive);
		my $d = difftime($first_message_publish, $last_message_receive);
		print "$which_recv, $a, $b, $c, $d, $sendperiod, $sendrate, $meandelay\n";
		if (0 != $print_legenda) {
			print "which_recv, first_message_publish, last_message_publish, first_message_recv, last_message_recv, sendperiod, sendrate, meandelay\n";
		}
	}
	}


}

if (-1 == $#ARGV) {
	die "tell me which file to treat\n\n";
}

my @filenames = ();

if (-f $ARGV[0]) {
	push @filenames, $ARGV[0];
} elsif (-d $ARGV[0]) {
	opendir DIR, $ARGV[0] or die "can't read directory";
	my @files = readdir DIR;
	closedir DIR;
	foreach my $file (@files) {
		my $fname_complete = "$ARGV[0]/$file";
		if (-f $fname_complete) {
			push @filenames, $fname_complete;
		}
	}
} else {
	die "param should be the file to treat\n\n";
}

foreach (@filenames) {
my $file_to_open = $_;

open FIN, "<$file_to_open";
print "\n$file_to_open\n";

my $I_had_problems = 0;
my $state = 1;
my %sendtimes = ();
my $which_send = 0;
my $which_recv = 0;
my $num_experiments = 0;
my $first_message_publish = "";
my $last_message_publish = "";
my $first_message_receive = "";
my $last_message_receive = "";
while (my $riga = <FIN>) {
	chomp($riga);

if (6 == ((my $year, my $month, my $day, my $sendtime, my $device, my $corrId) = $riga =~ /(\d+)-(\d+)-(\d+) (\d+:\d+:\d+,\d+) .*\[SEND,SensorData,(.+),(\d+)]/)) {
	if (2 != $state) {
		die "SEND in a bad state\n";
	}
	my $uid = $device.".".$corrId;
	if (exists($sendtimes{$uid})) {
		die "duplicated unique id:".$uid."\n\n";
	}
	$sendtimes{$uid} = $sendtime;
	if ($first_message_publish eq "") {$first_message_publish = $sendtime;}
	$last_message_publish = $sendtime;
	$which_send++;
}
if (6 == ((my $year, my $month, my $day, my $recvtime, my $device, my $corrId) = $riga =~ /(\d+)-(\d+)-(\d+) (\d+:\d+:\d+,\d+) .*\[RECV,VDOrders,(.+),(\d+)]/)) {
	if (2 != $state) {
		print "\nRECV in a bad state\n";
	}
	my $uid = $device.".".$corrId;
	if (!exists($sendtimes{$uid})) {
		print "\nSEND not found:".$uid."\n\n";
	}
	my $elapsed = difftime($recvtime,$first_message_publish);
	my $elapsed_send = difftime($sendtimes{$uid},$first_message_publish);
	if (1 == $print_all_times and 1 == $saturated) {
		print "$uid, $elapsed_send, $elapsed\n";
	}
	if (1 == $print_all_times and 0 == $saturated) {
		my $elapsed_real = difftime($sendtimes{$uid},$recvtime);
		print "$uid, $elapsed_send, $elapsed, $elapsed_real\n";
	}
	if ($from_very_first_message == 0) {
		$elapsed = difftime($sendtimes{$uid},$recvtime);
	}
	$all_delays[$file_number][$which_recv] = $elapsed;
	$all_sends[$file_number][$which_recv] = $elapsed_send;

	if ($first_message_receive eq "") {$first_message_receive = $recvtime;}
	$last_message_receive = $recvtime;

#	print "datum $which_recv uid $uid elapsed $elapsed sendtime $sendtimes{$uid} first_send $first_message_publish recv_time $recvtime\n";
	$which_recv++;
#	print "found: $year, $month, $day, $sendtime, $device, $corrId, $s\n";
}

if (1 == ($riga =~ /Starting experiment/)) {
	if (0 != $which_recv and $which_recv!=$which_send) {
		print "mismatch between num of sends \{$which_send\} and recvs \{$which_recv\}\n\n";
		$I_had_problems = 1;
	}

	if (0!=$which_recv and !($first_message_publish eq "")) {
		&analysis_round($which_recv, $first_message_publish, $last_message_publish, $first_message_receive, $last_message_receive, 0);
	}


$first_message_publish = "";
$last_message_publish = "";
$first_message_receive = "";
$last_message_receive = "";
	$state = 2;
	$num_experiments ++;
	%sendtimes = ();
	$which_send = 0;
	$which_recv = 0;
	$first_message_publish = "";
	$file_number ++;
}
if (1 == ($riga =~ /configuration/)) {
	$state = 1;
}
}
close FIN;
if ($which_recv!=$which_send) {
	print "mismatch between num of sends and recvs in the end\n\n";
	$I_had_problems = 1;
}
if ($max_which_recv < $which_recv) {$max_which_recv = $which_recv;}

		my $justdoit = 1;
if (1 == $justdoit or (0 == $I_had_problems && "" != $first_message_publish && 0 == $print_all_times)) {
	&analysis_round($which_recv, $first_message_publish, $last_message_publish, $first_message_receive, $last_message_receive, 1);
}
}

if (1 == $formatted_all_data) {
	print "msg number, values\n";
	for (my $j=0;$j<$file_number;$j++) {
		print ", $filenames[$j]";
	}
	print "\n";
	for (my $i=0;$i<$max_which_recv;$i++) {
		print "$i";
		for (my $j=1;$j<=$file_number;$j++) {
			my $val = $all_delays[$j][$i];
			print ", $val";
		}
		print "\n";
	}
}
if (1 == $formatted_all_sends) {
	print "\n\nmsg number, values\n";
	for (my $j=0;$j<$file_number;$j++) {
		print ", $filenames[$j]";
	}
	print "\n";
	for (my $i=0;$i<$max_which_recv;$i++) {
		print "$i";
		for (my $j=1;$j<=$file_number;$j++) {
			my $val = $all_sends[$j][$i];
			print ", $val";
		}
		print "\n";
	}
}


