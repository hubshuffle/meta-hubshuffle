do_install_append () {
  # Allow sudo for hubshuffle user
  echo "hubshuffle ALL=(ALL) ALL" > ${D}${sysconfdir}/sudoers.d/hubshuffle
}

FILES_${PN} += " \
  ${sysconfdir}/sudoers.d/hubshuffle \
"
