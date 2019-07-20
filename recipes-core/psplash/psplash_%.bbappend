FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI += "\
file://psplash-start.service \
file://psplash-quit.service \
"

inherit systemd

do_install_append() {
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/psplash-start.service ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/psplash-quit.service ${D}/${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "psplash-start.service psplash-quit.service"

FILES_${PN} += "\
${systemd_unitdir}/system \
${systemd_unitdir}/system/psplash-start.service \
${systemd_unitdir}/system/psplash-quit.service \
"
