package ms.cloud.guru.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto 
{
	private UUID id;
	private String beeName;
	private String beerStyle;
	private Long upc;

}
