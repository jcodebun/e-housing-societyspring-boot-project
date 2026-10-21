package in.com.ehousing.service;

import java.util.List;


import in.com.ehousing.dto.MessageDTO;
import in.com.ehousing.exception.DuplicateRecordException;



public interface MessageServiceInt {

	public long add(MessageDTO dto) throws DuplicateRecordException;

	public void delete(MessageDTO dto);

	public MessageDTO findBypk(long pk);

	public MessageDTO findByMessageName(String name);

	public void update(MessageDTO dto) throws DuplicateRecordException;

	public List<MessageDTO> list();

	public List<MessageDTO> list(int pageNo, int pageSize);

	public List<MessageDTO> search(MessageDTO dto);

	public List<MessageDTO> search(MessageDTO dto, int pageNo, int pageSize);
	
	/* public Blob getImageById(long id) throws SerialException, SQLException; */
	

	

}
