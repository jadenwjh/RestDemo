package com.jadenwjh.restdemo.service

import com.jadenwjh.restdemo.exception.message.ElementNotFoundException
import com.jadenwjh.restdemo.exception.message.InvalidPostException
import com.jadenwjh.restdemo.model.Node
import com.jadenwjh.restdemo.model.NodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class NodeController {

    @Autowired
    private val service = NodeService()

    @GetMapping("/nodes")
    fun getNodes(): List<Node> = service.getAll()

    @GetMapping("/nodes/{name}")
    fun getNodeByName(@PathVariable name: String) = service.getByName(name)
        ?: throw ElementNotFoundException("Node with name $name not found")

    @PostMapping("/nodes")
    fun saveNode(@RequestBody node: Node): ResponseEntity<Any> {
        if (node.name.isBlank()) throw InvalidPostException("Name cannot be null or blank")
        service.save(node)
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(node.name)
                .toUri())
            .build()
    }

    @DeleteMapping("/nodes/delete/{name}")
    fun deleteNode(@PathVariable name: String) {
        val deleted = service.deleteByName(name)
        if (!deleted) throw ElementNotFoundException("Node with name $name not found")
    }
}