This README file contains information on the contents of meta-hubshuffle layer.

Please see the corresponding sections below for details.

Dependencies
============
  * poky@warrior
  * meta-raspberrypi@warrior
  * meta-openembedded@warrior
  * meta-kodi@84cd030ec8bee8203f5266d02284ef8f236a11e1

Configuration
============
 * Add your NetworkManager connection profiles under meta-hubshuffle/recipes-networking/networkmanager/files/*.nmconnection and add those files to the SRC_URI variable in networkmanager_%.bbappend.
 * The root password is defined in meta-hubshuffle/recipes-core/images/hubshuffle-image-kodi.bb
 * The hubshuffle user password is configured in meta-hubshuffle/recipes-core/hubshuffle-user/hubshuffle-user.bb

Issues
============
You need to add "warrior" to the compatible layers in meta-kodi/conf/layer.conf.

Instructions
============
Setup your build environment:

```less
. ./sources/poky/oe-init-build-env
```

Setup *conf/local.conf*, for example:

```less
MACHINE = "raspberrypi3-custom"
DL_DIR = "/opt/yocto/cache/downloads"
SSTATE_DIR = "/opt/yocto/cache/sstate"
DISTRO = "hubshuffle"
PACKAGE_CLASSES ?= "package_rpm"
EXTRA_IMAGE_FEATURES ?= "debug-tweaks"
USER_CLASSES ?= "buildstats image-mklibs image-prelink"
PATCHRESOLVE = "noop"
BB_DISKMON_DIRS ??= "\
    STOPTASKS,${TMPDIR},1G,100K \
    STOPTASKS,${DL_DIR},1G,100K \
    STOPTASKS,${SSTATE_DIR},1G,100K \
    STOPTASKS,/tmp,100M,100K \
    ABORT,${TMPDIR},100M,1K \
    ABORT,${DL_DIR},100M,1K \
    ABORT,${SSTATE_DIR},100M,1K \
    ABORT,/tmp,10M,1K"
CONF_VERSION = "1"

```

Setup *conf/bblayers.conf*, for example:

```less
BBPATH = "${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \
  /opt/yocto/workspace/sources/poky/meta \
  /opt/yocto/workspace/sources/poky/meta-poky \
  /opt/yocto/workspace/sources/poky/meta-yocto-bsp \
  /opt/yocto/workspace/sources/meta-openembedded/meta-oe \
  /opt/yocto/workspace/sources/meta-openembedded/meta-python \
  /opt/yocto/workspace/sources/meta-openembedded/meta-networking \
  /opt/yocto/workspace/sources/meta-openembedded/meta-multimedia \
  /opt/yocto/workspace/sources/meta-openembedded/meta-filesystems \
  /opt/yocto/workspace/sources/meta-raspberrypi \
  /opt/yocto/workspace/sources/meta-kodi \
  /opt/yocto/workspace/sources/meta-hubshuffle \
  "

```

Build the image:
```bash
bitbake hubshuffle-image-kodi
```

Write the image to an sd card:
```bash
OUT_DEV=<sd card drive name>
sudo dd if=./tmp/deploy/images/raspberrypi3-custom/hubshuffle-image-kodi-raspberrypi3-custom.rpi-sdimg of=/dev/${OUT_DEV} status=progress 
```

ToDo
============
 * Permissions need to be configured to allow reboot/shutdown permissions, these options are missing from the Kodi exit menu.
 * Partitioning hasn't been configured.