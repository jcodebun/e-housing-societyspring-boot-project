package in.com.ehousing.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.com.ehousing.dto.AllocateDTO;


public interface AllocateDAOInt {

	public long add(AllocateDTO dto);
	
	public void delete(AllocateDTO dto);
	
	public AllocateDTO findBypk(long pk);
	
	public AllocateDTO findByEmail(String email);
	
	public AllocateDTO findByUserName(String userName);
	
	public void update(AllocateDTO dto);
	
	public List<AllocateDTO> list();
	
	public List<AllocateDTO>list(int pageNo,int pageSize);
	
	public List<AllocateDTO> search(AllocateDTO dto);
	
	public List<AllocateDTO> search(AllocateDTO dto,int pageNo,int pageSize);
	
	public Blob getImageById(long id) throws SerialException, SQLException;
	
}
