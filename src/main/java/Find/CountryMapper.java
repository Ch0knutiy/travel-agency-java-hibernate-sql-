package Find;

import Entity.CountryEntity;

import java.util.List;

public class CountryMapper extends MapperAbstract<CountryEntity>{

    @Override
    protected Class<CountryEntity> getType() {
        return CountryEntity.class;
    }

    @Override
    protected String getTableName() {
        return "country";
    }

    public void printAll(List<CountryEntity> countries) {
        for (CountryEntity country: countries) {
            System.out.println( country.getId() + ". " + country.getCountry());
        }
    }

    public void printAllInfoCountries(List<CountryEntity> countries) {
        for(CountryEntity country: countries){
            System.out.println( "country: "+country.getCountry());
        }
        System.out.println();
    }
}
