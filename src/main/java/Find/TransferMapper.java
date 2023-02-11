package Find;

import Entity.TransferEntity;

import java.util.List;

public class TransferMapper extends MapperAbstract<TransferEntity>{
    @Override
    protected Class<TransferEntity> getType() {
        return TransferEntity.class;
    }

    @Override
    protected String getTableName() {
        return "transfer";
    }

    public void printAll(List<TransferEntity> transfers) {
        for (TransferEntity transfer: transfers) {
            System.out.println( transfer.getId() + ". " + transfer.getPost()+" "+ transfer.getReason());
        }
    }

    public void printAllInfoTransfers(List<TransferEntity> transfers) {
        for (TransferEntity transfer: transfers) {
            System.out.println("Transfer's num: "+transfer.getNum());
            System.out.println("Post: "+transfer.getPost());
            System.out.println("Reason: "+transfer.getReason());
            System.out.println("Date: "+transfer.getDate());
            System.out.println("***");
        }
        System.out.println();
    }

    public List<TransferEntity> findByNum(int num) {
        return findByParameter(".byNum", num);
    }

    public List<TransferEntity> findByPost(String post) {
        return findByParameter(".byPost", "%" + post + "%");
    }
}
