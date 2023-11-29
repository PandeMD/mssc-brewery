package ms.cloud.guru.web.controller.v2;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ms.cloud.guru.services.V2.BeerServiceV2;
import ms.cloud.guru.web.model.v2.BeerDtoV2;


@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

	private final BeerServiceV2 beerService;

	public BeerControllerV2(BeerServiceV2 beerService) {
		super();
		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDtoV2> getBeer(@PathVariable UUID beerId) {
		return new ResponseEntity<BeerDtoV2>(beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDtoV2> handlePost(@RequestBody BeerDtoV2 beerDto)
	{
		BeerDtoV2 saveDto = beerService.saveNewBeer(beerDto);
		
		HttpHeaders headers = new HttpHeaders();
//		todo add hostname to url
		headers.add("Location", "/api/v2/beer"+saveDto.getId().toString());
		
		return new ResponseEntity<BeerDtoV2>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDtoV2> hendleUpdate(@PathVariable UUID beerId,@RequestBody BeerDtoV2 beerDto)
	{
		beerService.updateBeer(beerId,beerDto);
		
		return new ResponseEntity<BeerDtoV2>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void hendleUpdate(@PathVariable UUID beerId)
	{
		beerService.deleteById(beerId);
	}

}
