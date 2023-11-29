package ms.cloud.guru.services.V2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ms.cloud.guru.web.model.BeerDto;
import ms.cloud.guru.web.model.v2.BeerDtoV2;

@Slf4j
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {

	@Override
	public BeerDtoV2 getBeerById(UUID beerId) {
		return BeerDtoV2.builder().id(UUID.randomUUID())
				.beeName("Galaxy Cat")
				.beerStyle(null)
				.build();
	}

	@Override
	public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
		return BeerDtoV2.builder()
				.id(UUID.randomUUID())
				.build();
	}
	
	@Override
	public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
	
	}

	@Override
	public void deleteById(UUID beerId) {
	 log.debug("Deleting a beer...");
	}

}
