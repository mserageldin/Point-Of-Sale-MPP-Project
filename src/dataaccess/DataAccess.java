package dataaccess;

import java.util.HashMap;

import business.Author;
import business.Book;
import business.LibraryMember; 

public interface DataAccess { 
	
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String,Author> readAuthorMap();
	
	public void saveNewMember(LibraryMember member);
	public void saveNewAuthor(Author member); 
	public void storeBook (Book book);
	public void saveMemberInfo(LibraryMember member);
}
