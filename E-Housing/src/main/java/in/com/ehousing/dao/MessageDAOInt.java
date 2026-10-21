
package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.com.ehousing.dto.MessageDTO;


public interface MessageDAOInt {

	public long add(MessageDTO dto);
	
	public void delete(MessageDTO dto);
	
	public MessageDTO findBypk(long pk);
	
	public MessageDTO findByName(String name);
	
	public void update(MessageDTO dto);
	
	public List<MessageDTO> list();
	
	public List<MessageDTO>list(int pageNo,int pageSize);
	
	public List<MessageDTO> search(MessageDTO dto);
	
	public List<MessageDTO> search(MessageDTO dto,int pageNo,int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	
}
