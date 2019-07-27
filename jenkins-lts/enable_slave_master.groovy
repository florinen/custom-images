import jenkins.security.s2m.AdminWhitelistRule
import jenkins.model.Jenkins
// This script will enable Slave ->Master

Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class)
.setMasterKillSwitch(false)