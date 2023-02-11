package Find;

import Entity.RouteEntity;

import java.util.List;

public class RouteMapper extends MapperAbstract<RouteEntity>{
    @Override
    protected Class<RouteEntity> getType() {
        return RouteEntity.class;
    }

    @Override
    protected String getTableName() {
        return "route";
    }

    public void printAll(List<RouteEntity> routes) {
        for (RouteEntity route: routes) {
            System.out.println( route.getId() + ". " + route.getName());
        }
    }

    public void printAllInfoRoutes(List<RouteEntity> routes) {
        for (RouteEntity route: routes) {
            System.out.println("Name: "+route.getName());
            System.out.println("City: "+route.getCityId().getCity());
            System.out.println("Duration: "+route.getDuration());
            System.out.println("Hotel: "+route.getHotelId().getName());
            System.out.println("Employee: "+route.getEmployeeId().getSurname());
            System.out.println("Company: "+route.getCompanyId().getName());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<RouteEntity> findByCity(Object city) {
        return findByParameter(".byCity", "%" + city + "%");
    }

    public List<RouteEntity> findByHotel(Object hotel) {
        return findByParameter(".byHotel", "%" + hotel + "%");
    }

    public List<RouteEntity> findByEmployee(Object employee) {
        return findByParameter(".byEmployee", "%" + employee + "%");
    }

    public List<RouteEntity> findByCompany(Object company) {
        return findByParameter(".byCompany", "%" + company + "%");
    }
}
