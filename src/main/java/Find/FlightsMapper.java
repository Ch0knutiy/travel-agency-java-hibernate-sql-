package Find;

import Entity.FlightsEntity;

import java.util.List;

public class FlightsMapper extends MapperAbstract<FlightsEntity>{
    @Override
    protected Class<FlightsEntity> getType() {
        return FlightsEntity.class;
    }

    @Override
    protected String getTableName() {
        return "flights";
    }

    public void printAll(List<FlightsEntity> flights) {
        for (FlightsEntity flight: flights) {
            System.out.println( flight.getNum() + ". " + flight.getDate());
        }
    }

    public void printAllInfoFlights(List<FlightsEntity> flights) {
        for (FlightsEntity flight: flights) {
            System.out.println("Num: "+flight.getNum());
            System.out.println("Date: "+flight.getTime());
            System.out.println("Aircraft: "+flight.getAircraft()+" "+"Class: " +flight.getClazz());
            System.out.println("Free seat: "+flight.getFree());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<FlightsEntity> findByNum(String num) {
        return findByParameter(".byNum", "%" + num + "%");
    }

    public List<FlightsEntity> findByAircraft(String aircraft) {
        return findByParameter(".byAircraft", "%" + aircraft + "%");
    }

    public List<FlightsEntity> findByClass(String clazz) {
        return findByParameter(".byClass", "%" + clazz + "%");
    }
}
