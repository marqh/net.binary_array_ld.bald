package net.bald.netcdf

import net.bald.Var
import ucar.nc2.Variable

/**
 * NetCDF implementation of [Var].
 */
class NetCdfVar(
    private val v: Variable
): Var {
    override val name: String get() = v.shortName
}