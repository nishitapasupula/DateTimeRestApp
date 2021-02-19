package com.nishi.springboot.demo.DateTimeApp.Rest;

import java.util.Map;
import java.util.Set;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class ZoneDateTimeRestController {
	public static final Set<String> allZoneIDs = ZoneId.getAvailableZoneIds();
	private final String defaultZoneId = "UTC";
	private DateTimeFormatter formatToday=DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss");
	@GetMapping("/getcurenttime")
	@ResponseBody
	public ResponseEntity<Map<String, String>> sayHello(@RequestParam(value = "timezone", defaultValue = defaultZoneId) String timeZone) {
		Instant nowUtc = Instant.now();
		if ( !allZoneIDs.contains(timeZone)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		ZonedDateTime currZonedDateTime = ZonedDateTime.ofInstant(nowUtc, ZoneId.of(timeZone));
	
		Map<String, String> result =  Map.of("zoneId", timeZone,
				"dateime",currZonedDateTime.format(formatToday)
				);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
