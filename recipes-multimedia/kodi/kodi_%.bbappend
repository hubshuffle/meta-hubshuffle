FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
file://0001-cmake-fix-ninja-generator-when-dupbuild-err.patch \
file://kodi-custom.service \
"

WINDOWSYSTEM = "raspberrypi"
ARM_INSTRUCTION_SET = "arm"
do_package_qa[noexec] = "1"

do_install_append() {
  install -d "${D}${systemd_unitdir}/system"
  rm ${D}/${systemd_unitdir}/system/kodi.service
  install -m 0644 ${WORKDIR}/kodi-custom.service ${D}/${systemd_unitdir}/system/
}

inherit systemd
SYSTEMD_SERVICE_${PN} = "kodi-custom.service"

FILES_${PN} += "\
${systemd_unitdir}/system/kodi-custom.service \
"
