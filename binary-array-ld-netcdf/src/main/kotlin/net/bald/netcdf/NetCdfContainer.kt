package net.bald.netcdf

import net.bald.Attribute
import net.bald.Container
import net.bald.Var
import net.bald.context.ModelContext
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.rdf.model.ResourceFactory
import org.apache.jena.rdf.model.ResourceFactory.createPlainLiteral
import org.apache.jena.rdf.model.ResourceFactory.createResource
import ucar.nc2.AttributeContainer
import ucar.nc2.Group
import ucar.nc2.Variable

/**
 * NetCDF implementation of [Container].
 * See [NetCdfRootContainer] for the root group representation,
 * and [NetCdfSubContainer] for sub-groups.
 */
abstract class NetCdfContainer(
    private val group: Group
): Container {
    abstract val context: ModelContext
    abstract val uriParser: UriParser

    override fun vars(): Sequence<Var> {
        return group.variables.asSequence().filter(::acceptVar).map(::toVar)
    }

    override fun subContainers(): Sequence<Container> {
        return group.groups.asSequence().filter(::acceptGroup).map(::subContainer)
    }

    private fun toVar(v: Variable): Var {
        return NetCdfVar(this, v)
    }

    private fun subContainer(group: Group): Container {
        return NetCdfSubContainer(this, group)
    }

    open fun acceptVar(v: Variable): Boolean {
        return true
    }

    open fun acceptGroup(group: Group): Boolean {
        return true
    }

    override fun attributes(): List<Attribute> {
        return group.attributes().let(::source).attributes()
    }

    private fun source(attrs: AttributeContainer): NetCdfAttributeSource {
        return NetCdfAttributeSource(this, attrs)
    }

    fun parseProperty(name: String): Property {
        return uriParser.parse(name)?.let(ResourceFactory::createProperty)
            ?: context.property(name)
            ?: childUri(name).let(ResourceFactory::createProperty)
    }

    fun parseRdfNode(value: String): RDFNode {
        return uriParser.parse(value)?.let(::createResource)
            ?: context.resource(value)
            ?: createPlainLiteral(value)
    }


    abstract fun childUri(name: String): String
}