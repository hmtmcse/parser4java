package com.hmtmcse.parser4java

import spock.lang.Specification

class YamlProcessorTest extends Specification{

    def "Yml As Map"(){
        expect: "Bismillah"
        YamlProcessor yamlProcessor = new YamlProcessor()
        println(yamlProcessor.ymlAsMap("G:\\webcommanderv4\\WebCommander\\grails-app\\conf\\application.yml"))
        println("Bismillah")
    }

}
