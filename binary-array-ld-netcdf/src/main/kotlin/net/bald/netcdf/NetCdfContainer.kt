package net.bald.netcdf

import net.bald.Container
import net.bald.Var
import ucar.nc2.NetcdfFile

/**
 * NetCDF implementation of [Container].
 */
class NetCdfContainer(
    private val file: NetcdfFile
): Container {
    override fun vars(): Sequence<Var> {
        return file.variables.asSequence().map(::NetCdfVar)
    }
}