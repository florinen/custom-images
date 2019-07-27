import hudson.security.csrf.DefaultCrumbIssuer
import jenkins.model.Jenkins

// This script will enable CSRF  
def instance = Jenkins.instance
instance.setCrumbIssuer(new DefaultCrumbIssuer(true))
instance.save()