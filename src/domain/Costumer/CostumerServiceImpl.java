import java.util.ArrayList;
import java.util.List;

public class CostumerServiceImpl implements CostumerService {
  private List<Costumer> costumerRepository;

  public CostumerServiceImpl() {
    this.costumerRepository = new ArrayList<Costumer>();
  }

  @Override
  public Costumer create(Costumer costumer) {
    costumer.setId(getNextId());
    costumerRepository.add(costumer);
    return costumer;
  }

  @Override
  public Costumer updateById(Long id, Costumer costumer) throws Exception {
    Costumer costumerFound = findByIdOrThrow(id);
    costumerFound.setName(costumer.getName());
    costumerFound.setEmail(costumer.getEmail());
    costumerFound.setPhone(costumer.getPhone());
    return costumer;
  }

  @Override
  public void deleteById(Long id) throws Exception {
    findByIdOrThrow(id);
    for (int i = 0; i < costumerRepository.size(); i++) {
      if (costumerRepository.get(i).getId().equals(id)) {
        costumerRepository.remove(i);
        break;
      }
    }
  }

  private Costumer findByIdOrThrow(Long id) throws Exception {
    for (Costumer costumer : costumerRepository) {
      if (costumer.getId().equals(id)) {
        return costumer;
      }
    }
    throw new Exception("Cliente não encontrado");
  }

  private Long getNextId() {
    return costumerRepository.stream().map(Costumer::getId).max(Long::compareTo).orElse(0L) + 1;
  }

  @Override
  public Costumer getById(Long id) throws Exception {
    return findByIdOrThrow(id);
  }

  @Override
  public List<Costumer> getAll() {
    return costumerRepository;
  }

  @Override
  public String toString(Costumer costumer) {
    return "{"
        + "id= "
        + costumer.getId()
        + ", name= '"
        + costumer.getName()
        + '\''
        + ", email= '"
        + costumer.getEmail()
        + '\''
        + ", phone= '"
        + costumer.getPhone()
        + '\''
        + "}";
  }
}
