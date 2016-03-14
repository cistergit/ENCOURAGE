/**
MarketManager.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

/*
For the moment, we fill up the tables: (device_id -> appliance_id -> room_id -> cell_id -> macrocell_id)
- h_measure using MeterReadings with type E-ELEC, device_id from the MeterReading.Meter
- h_occupancy_calculated and h_occupancy_parametrized using MeterReading with type A-OCCUP, ROOM_xxx as device_id, with xxx the room_id
- h_weather using MeterReadings with type W-RAIN, W-TMEAN, W-H, W-DD, MACRO_xxx as device_id, with xxx the macrocell_id
- production_tariff_price/price_local_markets and utility_contract_price/price_energy/price_local_markets using E-SELLEU/E-SELLDK and E-BUYEU/E-BUYDK


CREATE TABLE price_energy (
    macrocell_id character varying(20) NOT NULL,
    price_energy_time integer NOT NULL,
    price_energy_intraday integer NOT NULL,
    unit_type_id character varying(20) NOT NULL,
    price_energy_intraday_value double precision,
    price_desviation_down double precision,
    price_desviation_up double precision
);


open problems:
- energy_local_provided using E-SOLD. How do I express who I sold the energy to? (cell_id_to, cell_id_from)

*/






import encourager.generated.marketparticipant.*;
import encourager.generated.*;

import java.util.List;
import java.util.ArrayList;

public class MarketManager {
	private List<MarketParticipant> m_participants;
	String m_semantics;

	/*
The Common Market Model describes the market participants and the role they are assuming
in the market. Defined market roles are supplied by a list (MarketRoleKind). A Market
Participant could play several roles in a market.
	 */
	
	
	/*
roleType MarketRoleKind Defined using an enumerated list of types of
market roles for use when a finite list of types
are desired.
type String The kind of market roles that can be played by
parties for given domains within the electricity
market. Types are flexible using dataType of
string for free-entry of role types.
 */
	/* Semantics for the "MarketRoleKind"s:

energyServiceConsumer Energy service consumer.

generatorOwner Generator merchant owner.

generatorOperator Generator merchant operator.

transmissionServiceProvider Transmission service provider.

transmissionOwner Transmission owner.

transmissionOperator Transmission operator.

distributionProvider Distribution provider.

loadServingEntity Load serving entity.

purchasingSellingEntity Purchasing selling entity.

competitiveRetailer Competitive retailer.

reliabilityAuthority Reliability authority.

planningAuthority Planning authority.

balancingAuthority Balancing authority.

interchangeAuthority Interchange authority.

transmissionPlanner Transmission planner.

resourcePlanner Resource planner.

standardsDeveloper Standards developer.

complianceMonitor Compliance monitor.

BalanceResponsibleParty A party that has a contract proving financial security
and identifying balance responsibility with the
Imbalance Settlement Responsible of the Market
Balance Area entitling the party to operate in the
market. This is the only role allowing a party to
nominate energy on a wholesale level.
Additional information:
The meaning of the word "balance" in this context
signifies that the quantity contracted to provide or to
consume must be equal to the quantity really provided
or consumed. Equivalent to "Program responsible
party" in the Netherlands. Equivalent to "Balance
group manager" in Germany. Equivalent to "market
agent" in Spain.

BalanceSupplier A party that markets the difference between actual
metered energy consumption and the energy bought
with firm energy contracts by the Party Connected to
the Grid. In addition the Balance Supplier markets any
difference with the firm energy contract (of the Party
Connected to the Grid) and the metered production.
Additional information:
There is only one Balance Supplier for each
Accounting Point.

BillingAgent The party responsible for invoicing a concerned party.

BlockEnergyTrader A party that is selling or buying energy on a firm basis
(a fixed volume per market time period).

CapacityCoordinator A party, acting on behalf of the System Operators
involved, responsible for establishing a coordinated
Offered Capacity and/or Net Transfer Capacity (NTC)
and/or Available Transfer Capacity (ATC) between
several Market Balance Areas.

CapacityTrader A party that has a contract to participate in the
Capacity Market to acquire capacity through a
Transmission Capacity Allocator.
Note:
The capacity may be acquired on behalf of an
Interconnection Trade Responsible or for sale on
secondary capacity markets.

Consumer A party that consumes electricity.
Additional information:
This is a Type of Party Connected to the Grid.

ConsumptionResponsibleParty A party who can be brought to rights, legally and
financially, for any imbalance between enegry
nominated and consumed for all associated
Accounting Points.
Additional information:
This is a type of Balance Responsible Party.

ControlAreaOperator Responsible for :
1. The coordination of exchange programs between its
related Market Balance Areas and for the exchanges
between its associated Control Areas.
2. The load frequency control for its own area.
3. The coordination of the correction of time
deviations.

ControlBlockOperator Responsible for :
1. The coordination of exchanges between its
associated Control Blocks and the organisation of the
coordination of exchange programs between its related
Control Areas.
2. The load frequency control within its own block and
ensuring that its Control Areas respect their
obligations in respect to load frequency control and
time deviation.
3. The organisation of the settlement and/or
compensation between its Control Areas.

CoordinationCenterOperator Responsible for :
1. The coordination of exchange programs between its
related Control Blocks and for the exchanges between
its associated Coordination Center Zones.
2. Ensuring that its Control Blocks respect their
obligations in respect to load frequency control.
3. Calculating the time deviation in cooperation with
the associated coordination centers.
4. Carrying out the settlement and/or compensation
between its Control Blocks and against the other
Coordination Center Zones.

GridAccessProvider A party responsible for providing access to the grid
through an Accounting Point and its use for energy
consumption or production to the Party Connected to
the Grid.

GridOperator A party that operates one or more grids.

ImbalanceSettlementResponsible A party that is responsible for settlement of the
difference between the contracted quantities and the
realised quantities of energy products for the Balance
Responsible Parties in a Market Balance Area.
Note:
The Imbalance Settlement Responsible has not the
responsibility to invoice. The Imbalance Settlement
Responsible may delegate the invoicing responsibility
to a more generic role such as a Billing Agent.

InterconnectionTradeResponsible Is a Balance Responsible Party or depends on one.
They are recognized by the Nomination Validator for
the nomination of already allocated capacity.
Additional information:
This is a type of Balance Responsible Party.

MarketInformationAggregator Market Information Aggregator, A party that provides
market related information that has been compiled
from the figures supplied by different actors in the
market. This information may also be published or
distributed for general use.
Note:
The Market Information Aggregator may receive
information from any market participant that is relevant
for publication or distribution.

MarketOperator The unique power exchange of trades for the actual
delivery of energy that receives the bids from the
Balance Responsible Parties that have a contract to
bid. The Market Operator determines the market
energy price for the Market Balance Area after
applying technical constraints from the System
Operator. It may also establish the price for the
reconciliation within a Metering Grid Area.

MeterAdministrator A party responsible for keeping a database of meters.

MeterOperator A party responsible for installing, maintaining, testing,
certifying and decommissioning physical meters.

MeteredDataCollector A party responsible for meter reading and quality
control of the reading.

MeteredDataResponsible A party responsible for the establishment and
validation of metered data based on the collected data
received from the Metered Data Collector. The party is
responsible for the history of metered data for a
Metering Point.

MeteredDataAggregator A party responsible for the establishment and
qualification of metered data from the Metered Data
Responsible. This data is aggregated according to a
defined set of market rules.

MeteringPointAdministrator A party responsible for registering the parties linked to
the metering points in a Metering Grid Area. They are
also responsible for maintaining the Metering Point
technical specifications. They are responsible for
creating and terminating metering points.

MOLResponsible Responsible for the management of the available
tenders for all Acquiring System Operators to establish
the order of the reserve capacity that can be activated.

NominationValidator Has the responsibility of ensuring that all capacity
nominated is within the allowed limits and confirming
all valid nominations to all involved parties. They
inform the Interconnection Trade Responsible of the
maximum nominated capacity allowed. Depending on
market rules for a given interconnection the
corresponding System Operators may appoint one
Nomination Validator.

PartyConnectedToTheGrid A party that contracts for the right to consume or
produce electricity at an Accounting Point.

Producer A party that produces electricity.
Additional information:
This is a type of Party Connected to the Grid.

ProductionResponsibleParty A party who can be brought to rights, legally and
financially, for any imbalance between energy
nominated and produced for all associated Accounting
Points.
Additional information:
This is a type of Balance Responsible Party.

ReconciliationAccountable A party that is financially accountable for the
reconciled volume of energy products for a profiled
Accounting Point.

ReconciliationResponsible A party that is responsible for reconciling, within a
Metering Grid Area, the volumes used in the
imbalance settlement process for profiled Accounting
Points and the actual metered quantities.
Note:
The Reconciliation Responsible may delegate the
invoicing responsibility to a more generic role such as
a Billing Agent.

ReserveAllocator Informs the market of reserve requirements, receives
tenders against the requirements and in compliance
with the prequalification criteria, determines what
tenders meet requirements and assigns tenders.

ResourceProvider A role that manages a resource object and provides
the schedules for it

SchedulingCoordinator A party that is responsible for the schedule information
and its exchange on behalf of a Balance Responsible
Party. For example in the Polish market a Scheduling
Coordinator is responsible for information interchange
for scheduling and settlement.

SystemOperator A party that is responsible for a stable power system
operation
(including the organisation of physical balance)
through a transmission grid in a geographical area.
The System Operator will also determine and be
responsible for cross border capacity and exchanges.
If necessary they may reduce allocated capacity to
ensure operational stability. Transmission as
mentioned above means "the transport of electricity on
the extra high or high voltage network with a view to
its delivery to final customers or to distributors.
Operation of transmission includes as well the tasks of
system operation concerning its management of
energy flows, reliability of the system and availability
of all necessary system services." (definition taken
from the ENTSO-E RGCE Operation handbook
Glossary).
Note: additional obligations may be imposed through
local market rules.

TradeResponsibleParty A party who can be brought to rights, legally and
financially, for any imbalance between energy
nominated and consumed for all associated
Accounting Points.
Note:
A power exchange without any privileged
responsibilities acts as a Trade Responsible Party.
Additional information:
This is a type of Balance Responsible Party.

TransmissionCapacityAllocator Manages the allocation of transmission capacity for an
Allocated Capacity Area.
For explicit auctions:
The Transmission Capacity Allocator manages, on
behalf of the System Operators, the allocation of
available transmission capacity for an Allocated
capacity Area. They offer the available transmission
capacity to the market, allocates the available
transmission capacity to individual Capacity Traders
and calculates the billing amount of already allocated
capacities to the Capacity Traders.
	 */



	public static MarketManager Unmarshal(String xml, MarketManager existing_market_manager) throws Exception {
		MarketManager ret = null;
		if (existing_market_manager == null)
			ret = new MarketManager("");
		else
			ret = existing_market_manager;

		String temp = ""+xml;
		EncMarketParticipant encpar = encourager.generated.EncMarketParticipant.CreateEncMarketParticipant();

		int next=1;
		while (next>0) {
			next=temp.indexOf("<?", 3);
			String work = null;
			if (next > 0) {
				work = temp.substring(0,next);
				temp = temp.substring(next);
			} else {
				work = temp;
			}
			String semantics = MarketManager.InferSemantics(work);
			if (0 == "MarketParticipant".compareTo(semantics)) {
				ret.m_participants.add(encpar.Unmarshal(work));
			}
//			System.out.println(semantics+"\n\n");
		}

		return ret;
	}

	public MarketManager() {
		ResetMarketManager();
	}

	public MarketManager(String sem) {
		m_semantics = sem;
		ResetMarketManager();
	}

	public void ResetMarketManager() {
		m_participants = new ArrayList<MarketParticipant>();
		m_semantics = null;
	}

	public String getSemantics() {
		return m_semantics;
	}

	public boolean AddMarketParticipant(String cell_id, String local_market_desc, String local_market_id) throws Exception {
		MarketParticipant ai = new MarketParticipant();

		NameType nt1 = new NameType();
		NameType nt2 = new NameType();
		nt1.setName("market description");
		nt2.setName("market id");
		Name name1 = new Name();
		Name name2 = new Name();
		name1.setNameType(nt1);
		name2.setNameType(nt2);
		name1.setName(local_market_desc);
		name2.setName(local_market_id);

		ai.getNames().add(name1);
		ai.getNames().add(name2);
		StreetAddress sa = new StreetAddress();
		StreetDetail sd = new StreetDetail();
		sd.setAddressGeneral(cell_id);
		sa.setStreetDetail(sd);
		ai.setStreetAddress(sa);
		ai.setMRID(local_market_id+"/"+cell_id);
		MarketRole role = new MarketRole();
		role.setRoleType(MarketRoleKind.INTERCHANGE_AUTHORITY);
		ai.getMarketRole().add(role);

		m_participants.add(ai);
		return true;
	}

	public String ToSql() throws Exception {
		StringBuilder sb = new StringBuilder();
		for (java.util.Iterator<MarketParticipant> e = m_participants.iterator(); e.hasNext();) {
			String cell_id;
			String local_market_desc;
			String local_market_id;
			MarketParticipant ec = e.next();
			if (0 == "market description".compareTo(ec.getNames().get(0).getNameType().getName())) {
				local_market_desc = ec.getNames().get(0).getName();
				local_market_id = ec.getNames().get(1).getName();
			} else {
				local_market_desc = ec.getNames().get(1).getName();
				local_market_id = ec.getNames().get(0).getName();
			}
			String longcell = ec.getStreetAddress().getStreetDetail().getAddressGeneral();
			cell_id = longcell.substring(5);
			sb.append("INSERT INTO middleware.local_market (cell_id, local_market_desc, local_market_id) VALUES ('"
			+cell_id+"', '"+local_market_desc+"', '"+local_market_id+"');\n");
		}
		return sb.toString();
	}

	public String Marshal() {
		StringBuilder sb = new StringBuilder();
		EncMarketParticipant emr = EncMarketParticipant.CreateEncMarketParticipant();
		for (java.util.Iterator<MarketParticipant> e = m_participants.iterator(); e.hasNext();) {
			MarketParticipant ec = e.next();
			sb.append(emr.Marshal(ec));
		}
		return sb.toString();
	}

	public static String InferSemantics(String xmldata) throws Exception {
		String semantics = null;
		String pattern = "<(\\w+)";
		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = r.matcher(xmldata);
		if (m.find()) {
			semantics = m.group(0).substring(1);
		}
		return semantics;
	}

	public static void main(String[] args) throws Exception {
		MarketManager mr = new MarketManager("");
		mr.AddMarketParticipant("CISTER", "selling energy and codefish", "PT_ENERGY");
		String xmldata = mr.Marshal();
		System.out.println("XML data:\n"+xmldata);
		mr.ResetMarketManager();
		MarketManager mr2 = MarketManager.Unmarshal(xmldata, mr);
		System.out.println("XML data2:\n"+mr2.Marshal());

		String sql1 = mr.ToSql();
		System.out.println(sql1);
	}
}

