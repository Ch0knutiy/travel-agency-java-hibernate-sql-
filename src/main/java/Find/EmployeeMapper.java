package Find;

import Entity.EmployeeEntity;

import java.util.List;

public class EmployeeMapper extends MapperAbstract<EmployeeEntity>{
    @Override
    protected Class<EmployeeEntity> getType() {
        return EmployeeEntity.class;
    }

    @Override
    protected String getTableName() {
        return "employee";
    }

    public void printAll(List<EmployeeEntity> employees) {
        for (EmployeeEntity employee: employees) {
            System.out.println( employee.getId() + ". " +employee.getSurname() +" "+employee.getName()+" "+employee.getPatronymic());
        }
    }

    public void printAllInfoEmployees(List<EmployeeEntity> employees) {
        for (EmployeeEntity employee: employees) {
            System.out.println("Employee: "+employee.getSurname()+" "+employee.getName()+" "+employee.getPatronymic());
            System.out.println("Phone: "+employee.getPhone());
            System.out.println("Address: "+employee.getAddress());
            System.out.println("Post: "+employee.getPost());
            System.out.println("Salary: "+employee.getSalary());
            System.out.println("Transfer: "+((employee.getTransferId() == null)? "None" : employee.getTransferId().getNum()));
            System.out.println("***");
        }
        System.out.println();
    }

    public List<EmployeeEntity> findByPhone(String phone) {
        return findByParameter(".byPhone", "%" + phone + "%");
    }

    public List<EmployeeEntity> findByAddress(String address) {
        return findByParameter(".byAddress", "%" + address + "%");
    }

    public List<EmployeeEntity> findByPost(String post) {
        return findByParameter(".bypost", "%" + post + "%");
    }

    public List<EmployeeEntity> findBySalary(int salary) {
        return findByParameter(".bySalary", "%" + salary + "%");
    }

    public List<EmployeeEntity> findByTransfer(int transfer) {
        return findByParameter(".byTransfer", "%" + transfer + "%");
    }
}
