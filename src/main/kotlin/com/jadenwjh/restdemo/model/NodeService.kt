package com.jadenwjh.restdemo.model

import org.springframework.stereotype.Service

@Service
class NodeService {

    companion object {
        lateinit var nodes: MutableList<Node>
    }

    init {
        nodes = mutableListOf()

        // Mock
        nodes.add(Node("Example1", "img_url1"))
        nodes.add(Node("Example2", "img_url2"))
    }

    fun getAll(): List<Node> = nodes

    fun getByName(matchingName: String): Node? = nodes.stream()
        .filter { node -> node.name == matchingName }
        .findFirst()
        .orElse(null)

    fun saveNode(node: Node) = nodes.add(node)
}