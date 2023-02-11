package Find;

import Entity.CompanyEntity;

import java.util.List;

public class CompanyMapper extends MapperAbstract<CompanyEntity>{

    @Override
    protected Class<CompanyEntity> getType() {
        return CompanyEntity.class;
    }

    @Override
    protected String getTableName() {
        return "company";
    }

    public void printAll(List<CompanyEntity> companies) {
        for (CompanyEntity company: companies) {
            System.out.println( company.getId() + ". " + company.getName());
        }
    }

    public void printAllInfoCompany(List<CompanyEntity> companies) {
        for (CompanyEntity company: companies) {
            System.out.println("Company: "+company.getName());
            System.out.println("Flight's num: "+company.getNum().getNum());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<CompanyEntity> findByNum(String flights_num) {
        return findByParameter(".byFlights", "%" + flights_num + "%");
    }
}
