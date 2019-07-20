inherit useradd
USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-G audio,input,plugdev,sudo,video,shutdown,dialout -P hubshuffle -u 1000 -d /home/hubshuffle -m -r -s /bin/bash hubshuffle;"

LICENSE = "CLOSED"

EXCLUDE_FROM_WORLD = "1"

S = "${WORKDIR}"

SRC_URI = "\
file://hubshuffle-fix-user-permissions.sh \
file://hubshuffle-fix-user-permissions.service \
"

do_install () {
  install -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/hubshuffle-fix-user-permissions.sh ${D}${bindir}/
  
  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/hubshuffle-fix-user-permissions.service ${D}/${systemd_unitdir}/system
}

inherit systemd
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "hubshuffle-fix-user-permissions.service"

FILES_${PN} = " \
  ${bindir}/hubshuffle-fix-user-permissions.sh \
  ${systemd_unitdir}/system/hubshuffle-fix-user-permissions.service \
"
