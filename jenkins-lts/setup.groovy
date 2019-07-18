import jenkins.model.*
import hudson.security.*
import hudson.*
import hudson.model.*
import jenkins.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*

def env = System.getenv()

// Create user 
def jenkins = Jenkins.getInstance()
if(!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm))
    jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))

if(!(jenkins.getAuthorizationStrategy() instanceof GlobalMatrixAuthorizationStrategy))
    jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()
jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)

// Set access control for builds
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class)
.setMasterKillSwitch(false)
// Save Jenkins
jenkins.save()

// Set URL and email
// parameters

def jenkinsParameters = [
  email:  'Jenkins Admin <admin@jenkins.com>',
//url:    'https://ci.jenkins.com:8083/'
]
// get Jenkins location configuration

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
//jenkinsLocationConfiguration.setUrl(jenkinsParameters.url)
jenkinsLocationConfiguration.setAdminAddress(jenkinsParameters.email)
// Save jenkinsLocationConfiguration
jenkinsLocationConfiguration.save()


