package com.jadenwjh.restdemo.service

import com.jadenwjh.restdemo.model.Node
import com.jadenwjh.restdemo.model.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NodeController {

    @Autowired
    private val service = NodeService()

    @GetMapping("/nodes")
    fun getNodes(): List<Node> = service.getAll()
}