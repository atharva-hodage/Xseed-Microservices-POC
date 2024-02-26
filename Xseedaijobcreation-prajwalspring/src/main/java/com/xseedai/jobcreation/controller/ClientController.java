package com.xseedai.jobcreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseedai.jobcreation.entity.ClientMaster;
import com.xseedai.jobcreation.service.ClientService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/jobcreation")
@AllArgsConstructor
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping("/addclient/{vmsId}")
	public ResponseEntity<ClientMaster> createClient(@RequestBody ClientMaster clientMaster, @PathVariable Long vmsId) {
		try {
			ClientMaster createdClient = clientService.createClient(clientMaster, vmsId);
			return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getclient/{vmsId}")
	public ResponseEntity<List<ClientMaster>> getClientsByVmsId(@PathVariable Long vmsId) {
		List<ClientMaster> clients = clientService.getClientsByVmsId(vmsId);
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
}
