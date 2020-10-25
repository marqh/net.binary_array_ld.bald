package net.bald.netcdf

import net.bald.BinaryArray
import net.bald.Container
import ucar.nc2.NetcdfFile
import ucar.nc2.NetcdfFiles
import java.io.Closeable
import java.io.File

/**
 * NetCDF implementation of [BinaryArray].
 * Should be closed after use.
 */
class NetCdfBinaryArray(
    override val uri: String,
    private val file: NetcdfFile
): BinaryArray, Closeable {
    override val root: Container get() = NetCdfContainer(file.rootGroup)

    override fun close() {
        file.close()
    }

    companion object {
        @JvmStatic
        fun create(fileLoc: String, uri: String? = null): NetCdfBinaryArray {
            TODO()
        }
    }
}