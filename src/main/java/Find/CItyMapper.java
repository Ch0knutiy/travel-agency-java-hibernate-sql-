package Find;

import Entity.CityEntity;

import java.util.List;

public class CItyMapper extends MapperAbstract<CityEntity> {

    @Override
    protected Class<CityEntity> getType() {
        return CityEntity.class;
    }

    @Override
    protected String getTableName() {
        return "city";
    }

    public void printAll(List<CityEntity> cities) {
        for (CityEntity city: cities) {
            System.out.println( city.getId() + ". " + city.getCity());
        }
    }

    public void printAllInfoCities(List<CityEntity> cities) {
        for (CityEntity city: cities) {
            System.out.println("City: "+city.getCity()+" Country: "+ city.getCountry().getCountry());
        }
        System.out.println();
    }

    public List<CityEntity> findByCountry(Object countryName) {
        return findByParameter(".byCountry", "%" + countryName + "%");
    }
}
