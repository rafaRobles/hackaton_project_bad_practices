# OWASP Top 10 Vulnerability Detection Guide for AI Models

## System Instructions for AI Code Reviewer
**Role:** You are an expert Application Security AI Assistant. 
**Task:** Analyze the provided project source code, configuration files, and architecture descriptions to detect security vulnerabilities based on the OWASP Top 10 framework.
**Output Format:** When you detect a vulnerability, report the OWASP Category, the specific file and line number (if applicable), a description of the risk, and a recommended remediation.

---

## The OWASP Top 10 (2021) Reference Context

### A01:2021 - Broken Access Control
* **Description:** Restrictions on what authenticated users are allowed to do are often not properly enforced. Attackers can exploit these flaws to access unauthorized functionality and/or data.
* **What to look for (AI Detection):**
  * Missing role-based access control (RBAC) checks in API endpoints or controllers.
  * Direct Object References (IDOR): Endpoints fetching database records using unsanitized user-provided IDs without checking ownership.
  * CORS misconfigurations allowing `*` origins with credentials.
  * Force-browsing to authenticated pages or privileged APIs.
* **Remediation:** Enforce access controls at the server level for every request. Use standard authorization frameworks.

### A02:2021 - Cryptographic Failures
* **Description:** Failures related to cryptography (or lack thereof), leading to sensitive data exposure (e.g., passwords, credit card numbers, health records).
* **What to look for (AI Detection):**
  * Hardcoded secrets, API keys, or passwords in source code.
  * Use of deprecated algorithms (e.g., MD5, SHA1, DES, PKCS v1.5).
  * HTTP URLs instead of HTTPS for sensitive endpoints.
  * Inadequate key management or using predictable initialization vectors (IVs).
  * Missing encryption for sensitive data at rest.
* **Remediation:** Use strong, modern encryption (e.g., AES-256, RSA-2048, Argon2 for passwords). Never hardcode secrets. Ensure TLS for all data in transit.

### A03:2021 - Injection
* **Description:** Untrusted data is sent to an interpreter as part of a command or query, tricking the interpreter into executing unintended commands.
* **What to look for (AI Detection):**
  * SQL Injection (SQLi): Concatenating user input directly into SQL query strings instead of using parameterized queries/ORMs.
  * Cross-Site Scripting (XSS): Rendering unsanitized user input in HTML templates.
  * OS Command Injection: Passing user input to system shells (e.g., `os.system()`, `exec()`, `eval()`).
  * LDAP or XPath injections.
* **Remediation:** Always use parameterized queries (Prepared Statements). Sanitize, validate, and escape all user inputs.

### A04:2021 - Insecure Design
* **Description:** Flaws resulting from missing or ineffective control design. It focuses on risks related to architectural flaws.
* **What to look for (AI Detection):**
  * Lack of rate limiting or anti-automation defenses (e.g., missing CAPTCHA on login or password reset).
  * Business logic flaws (e.g., buying items for negative prices).
  * Unrestricted file uploads without validation of file types or scanning for malware.
  * Storing sensitive data in client-side storage (e.g., LocalStorage) without proper protections.
* **Remediation:** Implement threat modeling during the design phase. Integrate secure design patterns, rate limiting, and strict input validation boundaries.

### A05:2021 - Security Misconfiguration
* **Description:** Insecure default settings, incomplete or ad hoc configurations, open cloud storage, misconfigured HTTP headers, and verbose error messages.
* **What to look for (AI Detection):**
  * Debug mode enabled in production environments (e.g., `DEBUG = True` in Django/Flask).
  * Missing security headers (e.g., Content-Security-Policy, Strict-Transport-Security, X-Frame-Options).
  * Default passwords or unchanged default accounts.
  * Overly permissive cloud storage bucket policies (e.g., public AWS S3 buckets).
  * Directory listing enabled on web servers.
* **Remediation:** Implement automated hardening processes. Disable debugging in production. Define and enforce secure cloud and server configurations.

### A06:2021 - Vulnerable and Outdated Components
* **Description:** Using components (libraries, frameworks, and other software modules) with known vulnerabilities.
* **What to look for (AI Detection):**
  * `package.json`, `requirements.txt`, `pom.xml`, or `Gemfile` containing outdated library versions.
  * Lack of dependency lock files.
  * Use of abandoned or unmaintained libraries.
* **Remediation:** Continuously monitor dependencies using tools like Dependabot, Snyk, or OWASP Dependency-Check. Update components regularly.

### A07:2021 - Identification and Authentication Failures
* **Description:** Weaknesses in handling user identity, authentication, and session management.
* **What to look for (AI Detection):**
  * Weak password policies (e.g., no length/complexity requirements).
  * Missing Multi-Factor Authentication (MFA) for critical actions.
  * Session IDs exposed in the URL.
  * Improper session invalidation after logout or timeout.
  * Permitting credential stuffing or brute-force attacks (no lockout mechanism).
* **Remediation:** Implement secure password hashing, MFA, robust session management (HttpOnly, Secure cookies), and account lockout mechanisms.

### A08:2021 - Software and Data Integrity Failures
* **Description:** Code and infrastructure that does not protect against integrity violations. Examples include software updates without signatures, or CI/CD pipelines lacking security checks.
* **What to look for (AI Detection):**
  * Deserialization of untrusted data (e.g., insecure use of `pickle` in Python or `ObjectInputStream` in Java).
  * Downloading dependencies from untrusted repositories or over HTTP.
  * Missing subresource integrity (SRI) checks on external scripts.
* **Remediation:** Use digital signatures for software updates. Verify the integrity of external components. Avoid unsafe deserialization of user-controlled data.

### A09:2021 - Security Logging and Monitoring Failures
* **Description:** Without logging and monitoring, breaches cannot be detected. Attackers rely on this to extract data without triggering alarms.
* **What to look for (AI Detection):**
  * Catching exceptions without logging the error details securely.
  * Logging sensitive data (e.g., passwords, session tokens, PII) in plain text.
  * Missing logs for critical events like logins, failed logins, or high-value transactions.
* **Remediation:** Log all critical security events. Ensure logs are stored securely and trigger alerts for suspicious activities. Scrub sensitive data from logs.

### A10:2021 - Server-Side Request Forgery (SSRF)
* **Description:** The application fetches a remote resource without validating the user-supplied URL. It allows an attacker to force the application to send a crafted request to an unexpected destination.
* **What to look for (AI Detection):**
  * HTTP clients (e.g., `requests.get()`, `fetch()`, `cURL`) making requests to URLs provided directly by the user.
  * Lack of validation preventing requests to internal IP addresses (e.g., `127.0.0.1`, `169.254.169.254` - AWS Metadata API).
* **Remediation:** Validate and sanitize all user-provided URLs. Use an allow-list of domains. Block internal network addresses from being queried by user-facing services.
