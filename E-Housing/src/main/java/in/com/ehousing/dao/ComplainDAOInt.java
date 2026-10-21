package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.com.ehousing.dto.ComplainDTO;


public interface ComplainDAOInt {

	public long add(ComplainDTO dto);
	
	public void delete(ComplainDTO dto);
	
	public ComplainDTO findBypk(long pk);
	
	public ComplainDTO findByName(String name);
	
	public void update(ComplainDTO dto);
	
	public List<ComplainDTO> list();
	
	public List<ComplainDTO>list(int pageNo,int pageSize);
	
	public List<ComplainDTO> search(ComplainDTO dto);
	
	public List<ComplainDTO> search(ComplainDTO dto,int pageNo,int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	
}
