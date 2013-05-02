DESCRIPTON = "Generic startup script for exosense device system. Stolen from Yocto Cookbook wiki"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Recipe revision - don't forget to 'bump' when a new revision is created !
PR = "r0"

# Runtime dependencies
#
# Add a line similar to the following to ensure any packages needed for the scripts to run are installed in the image.
#
RDEPENDS_${PN} = "exosense "

# SRC_URI specifies the files that are to be used as the scripts.
#
# Any valid src_uri format can be used - this example assumes the
# scripts are stored in the 'files' directory below the one in
# which this file is located.
# The script should support the start and stop arguments.
#
SRC_URI = "\
   file://exodev_ctl \
   "

# This function is responsible for:
#  1) Ensuring the required directories exist in the image;
#  2) Installing the scripts in to the image;
#  3) Creating the desired runlevel links to the scripts.
#
do_install() {
    #
    # Create directories:
    #   ${D}${sysconfdir}/init.d - will hold the scripts
    #   ${D}${sysconfdir}/rcS.d  - will contain a link to the script that runs at startup
    #   ${D}${sysconfdir}/rc5.d  - will contain a link to the script that runs at runlevel=5
    #   ${D}${sbindir}           - scripts called by the above
    #
    # ${D} is effectively the root directory of the target system.
    # ${D}${sysconfdir} is where system configuration files are to be stored (e.g. /etc).
    # ${D}${sbindir} is where executable files are to be stored (e.g. /sbin).
    #
    install -d ${D}${sysconfdir}/init.d
    # Removed multiple runlevel installation to avoid multiple starts during boot
    #install -d ${D}${sysconfdir}/rcS.d
    #install -d ${D}${sysconfdir}/rc1.d
    #install -d ${D}${sysconfdir}/rc2.d
    #install -d ${D}${sysconfdir}/rc3.d
    #install -d ${D}${sysconfdir}/rc4.d
    install -d ${D}${sysconfdir}/rc5.d

    #
    # Install files in to the image
    #
    # The files fetched via SRC_URI (above) will be in ${WORKDIR}.
    #
    install -m 0755 ${WORKDIR}/exodev_ctl  ${D}${sysconfdir}/init.d/

    #
    # Create symbolic links from the runlevel directories to the script files.
    # Links of the form S... and K... mean the script when be called when
    # entering / exiting the runlevel designated by the containing directory.
    # For example:
    #   rc5.d/S90run-script will be called (with %1='start') when entering runlevel 5.
    #   rc5.d/K90run-script will be called (with %1='stop') when exiting runlevel 5.
    #
    # Removed multiple linking to avoid multiple start invocation during boot
#    ln -sf ../init.d/exodev_ctl     ${D}${sysconfdir}/rcS.d/S90exodev_ctl
#    ln -sf ../init.d/exodev_ctl      ${D}${sysconfdir}/rc1.d/K90exodev_ctl
#    ln -sf ../init.d/exodev_ctl      ${D}${sysconfdir}/rc2.d/K90exodev_ctl
#    ln -sf ../init.d/exodev_ctl      ${D}${sysconfdir}/rc3.d/K90exodev_ctl
#    ln -sf ../init.d/exodev_ctl      ${D}${sysconfdir}/rc4.d/K90exodev_ctl
    ln -sf ../init.d/exodev_ctl      ${D}${sysconfdir}/rc5.d/S90exodev_ctl
}