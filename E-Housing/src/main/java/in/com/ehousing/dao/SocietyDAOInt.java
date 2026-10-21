package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.com.ehousing.dto.SocietyDTO;


public interface SocietyDAOInt {

	public long add(SocietyDTO dto);
	
	public void delete(SocietyDTO dto);
	
	public SocietyDTO findBypk(long pk);
	
	public SocietyDTO findByName(String name);
	
	public void update(SocietyDTO dto);
	
	public List<SocietyDTO> list();
	
	public List<SocietyDTO>list(int pageNo,int pageSize);
	
	public List<SocietyDTO> search(SocietyDTO dto);
	
	public List<SocietyDTO> search(SocietyDTO dto,int pageNo,int pageSize);
	
	public Blob getImageById(long id) throws SerialException, SQLException;
	
}
