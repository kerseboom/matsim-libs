package playground.sergioo.passivePlanning2012.core.population;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.population.PersonImpl;
import org.matsim.core.population.PersonUtils;
import org.matsim.core.population.PlanImpl;
import org.matsim.core.router.TripRouter;
import org.matsim.facilities.ActivityFacilities;

import playground.sergioo.passivePlanning2012.api.population.BasePerson;
import playground.sergioo.passivePlanning2012.api.population.BasePlan;

public class BasePersonImpl extends PersonImpl implements BasePerson {

	//Attributes
	private BasePlan basePlan = new BasePlanImpl(this);
	
	//Constructors
	public BasePersonImpl(Id<Person> id) {
		super(id);
	}

	//Static methods
	public static BasePersonImpl getBasePerson(boolean fixedTypes, String[] types, Person person, TripRouter tripRouter, ActivityFacilities facilities) {
		BasePersonImpl newPerson = new BasePersonImpl(person.getId());
		PersonUtils.setAge(newPerson, PersonUtils.getAge(person));
		PersonUtils.setCarAvail(newPerson, PersonUtils.getCarAvail(person));
		PersonUtils.setEmployed(newPerson, PersonUtils.isEmployed(person));
		PersonUtils.setLicence(newPerson, PersonUtils.getLicense(person));
		PersonUtils.setSex(newPerson, PersonUtils.getSex(person));
		PlanImpl plan = (PlanImpl) person.getSelectedPlan();
		newPerson.addPlan(plan);
		BasePlanImpl.createBasePlan(fixedTypes, types, newPerson, plan, tripRouter, facilities);
		return newPerson;
	}
	public static BasePersonImpl convertToBasePerson(Person person) {
		BasePersonImpl newPerson = new BasePersonImpl(person.getId());
		PersonUtils.setAge(newPerson, PersonUtils.getAge(person));
		PersonUtils.setCarAvail(newPerson, PersonUtils.getCarAvail(person));
		PersonUtils.setEmployed(newPerson, PersonUtils.isEmployed(person));
		PersonUtils.setLicence(newPerson, PersonUtils.getLicense(person));
		PersonUtils.setSex(newPerson, PersonUtils.getSex(person));
		for(Plan plan:person.getPlans())
			if(!plan.isSelected())
				newPerson.addPlan(plan);
		BasePlanImpl.convertToBasePlan(newPerson, person.getSelectedPlan());
		return newPerson;
	}
	
	//Methods
	@Override
	public BasePlan getBasePlan() {
		return basePlan;
	}
	public void setBasePlan(BasePlan basePlan) {
		this.basePlan = basePlan;
	}

}
