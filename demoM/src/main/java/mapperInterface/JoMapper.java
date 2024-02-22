package mapperInterface;

import java.util.List;

import com.example.demo.domain.JoDTO;

public interface JoMapper {
	
	List<JoDTO> joSelectList();
	
	JoDTO joSelectOne(int jno);

	int joupdate(JoDTO jdto);

	int joinsert(JoDTO jdto);

	int joDelete(int jno);

}
