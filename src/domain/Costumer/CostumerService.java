import java.util.List;

interface CostumerService {
  Costumer create(Costumer costumer);

  Costumer updateById(Long id, Costumer costumer) throws Exception;

  void deleteById(Long id) throws Exception;

  Costumer getById(Long id) throws Exception;

  List<Costumer> getAll();

  String toString(Costumer costumer);
}
