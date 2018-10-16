var loginUserId = "${loginUserId}";
var rsa_modulus = "${rsa_modulus}";
var rsa_exponent = "${rsa_exponent}";
var shouldChangePassword = "${shouldChangePassword}";
var rsa_key = RSAUtils.getKeyPair(rsa_exponent, '', rsa_modulus);
var activeTimeoutObject = {};
activeTimeoutObject.configValue = "${sysConfActiveTimeout.configValue}";