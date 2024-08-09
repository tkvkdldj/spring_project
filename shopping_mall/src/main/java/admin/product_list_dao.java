package admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product_list_dao {
	int pidx;
	String pdprice, pd_discount, pd_disprice, pdstock; //넘어오는 값은 전부 String이니까?
	String csortcode, pdcode, pdname, pdaddexplan, is_sale, pdorimage, pdreimage, pdate;
	
}
