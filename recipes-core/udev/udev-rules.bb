DESCRIPTION = "udev rules for Raspberry Pi Boards"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
file://10-vchiq-permissions.rules \
file://99-media-automount.rules \
file://usb-mount@.service \
file://usb-mount.sh \
"

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_install () {
  install -d ${D}${sysconfdir}/udev/rules.d
  install -m 0644 ${WORKDIR}/10-vchiq-permissions.rules ${D}${sysconfdir}/udev/rules.d/
  install -m 0644 ${WORKDIR}/99-media-automount.rules ${D}${sysconfdir}/udev/rules.d/
  
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/usb-mount.sh ${D}${bindir}/
  
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/usb-mount@.service ${D}/${systemd_unitdir}/system
}

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "usb-mount@.service"

FILES_${PN} = "\
${sysconfdir}/udev/rules.d/10-vchiq-permissions.rules \
${sysconfdir}/udev/rules.d/99-media-automount.rules \
${bindir}/usb-mount.sh \
${systemd_unitdir}/system/usb-mount@.service \
"
