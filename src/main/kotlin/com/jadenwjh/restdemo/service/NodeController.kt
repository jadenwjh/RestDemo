package com.jadenwjh.restdemo.service

import com.jadenwjh.restdemo.exception.message.ElementNotFoundException
import com.jadenwjh.restdemo.model.Node
import com.jadenwjh.restdemo.model.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class NodeController {

    @Autowired
    private val service = NodeService()

    @GetMapping("/nodes")
    fun getNodes(): List<Node> = service.getAll()

    @GetMapping("/nodes/{name}")
    fun getNodeByName(@PathVariable name: String) = service.getByName(name)
        ?: throw ElementNotFoundException("Node with name $name not found")
}