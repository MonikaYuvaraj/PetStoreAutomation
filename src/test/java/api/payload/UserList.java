package api.payload;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
	 public int page;
	    public int per_page;
	    public int total;
	    public int total_pages;
	    public ArrayList<UserDetails> data;
	    public Support support;
}
