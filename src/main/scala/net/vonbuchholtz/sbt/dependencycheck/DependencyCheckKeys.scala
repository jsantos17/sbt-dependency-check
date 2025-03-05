package net.juanpablosantos.sbt.dependencycheck

import sbt.*

trait DependencyCheckKeys {

	// Configuration
	lazy val dependencyCheckAutoUpdate = settingKey[Option[Boolean]]("Sets whether auto-updating of the NVD CVE/CPE, retireJS and hosted suppressions data is enabled. It is not recommended that this be turned to false.")
	lazy val dependencyCheckCveValidForHours = settingKey[Option[Int]]("Sets the number of hours to wait before checking for new updates from the NVD.")
	lazy val dependencyCheckFailBuildOnCVSS = settingKey[Float]("Specifies if the build should be failed if a CVSS score above a specified level is identified. The default is 11 which means since the CVSS scores are 0-10, by default the build will never fail. More information on CVSS scores can be found at https://nvd.nist.gov/vuln-metrics/cvss")
  lazy val dependencyCheckJUnitFailBuildOnCVSS = settingKey[Option[Float]]("If using the JUNIT report format the dependencyCheckJUnitFailOnCVSS sets the CVSS score threshold that is considered a failure.")
  lazy val dependencyCheckFormat = settingKey[String]("The report format to be generated (HTML, XML, JUNIT, CSV, JSON, SARIF, JENKINS, ALL). This setting is ignored if dependencyCheckReportFormats is set.")
  lazy val dependencyCheckFormats = settingKey[Seq[String]]("A sequence of report formats to be generated (HTML, XML, JUNIT, CSV, JSON, SARIF, JENKINS, ALL).")
  lazy val dependencyCheckOutputDirectory = settingKey[Option[File]]("The location to write the report(s).")
  lazy val dependencyCheckScanSet = settingKey[Seq[File]]("An optional sequence of files that specify additional files and/or directories to analyze as part of the scan. If not specified, defaults to standard scala conventions.")
  lazy val dependencyCheckSkip = settingKey[Boolean]("Skips the dependency-check analysis")
  lazy val dependencyCheckSkipTestScope = settingKey[Boolean]("Skips analysis for artifacts with Test Scope")
  lazy val dependencyCheckSkipRuntimeScope = settingKey[Boolean]("Skips analysis for artifacts with Runtime Scope")
  lazy val dependencyCheckSkipProvidedScope = settingKey[Boolean]("Skips analysis for artifacts with Provided Scope")
  lazy val dependencyCheckSkipOptionalScope = settingKey[Boolean]("Skips analysis for artifacts with Optional Scope")
  lazy val dependencyCheckSuppressionFile = settingKey[Option[File]]("The file path to the XML suppression file - used to suppress false positives. If you want to add multiple files use dependencyCheckSuppressionFiles instead.")
  lazy val dependencyCheckSuppressionFiles = settingKey[Seq[File]]("The sequence of file paths to the XML suppression files - used to suppress false positives")
  lazy val dependencyCheckCpeStartsWith = settingKey[Option[String]]("The starting String to identify the CPEs that are qualified to be imported.")
  lazy val dependencyCheckHintsFile = settingKey[Option[File]]("The file path to the XML hints file - used to resolve false negatives.")
	lazy val dependencyCheckUseSbtModuleIdAsGav = settingKey[Option[Boolean]]("Uses the SBT ModuleId as GAV (reduces dependency on Maven Central for resolving)")
	lazy val dependencyCheckAnalysisTimeout = settingKey[Option[Int]]("Set the analysis timeout in minutes.")
  lazy val dependencyCheckEnableExperimental = settingKey[Option[Boolean]]("Enable the experimental analyzers. If not enabled the experimental analyzers (see below) will not be loaded or used.")
  lazy val dependencyCheckEnableRetired = settingKey[Option[Boolean]]("Enable the retired analyzers. If not enabled retired analyzers will not be loaded or used.")

  // Analyzer configuration
	lazy val dependencyCheckArchiveAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the Archive Analyzer will be used.")
	lazy val dependencyCheckZipExtensions = settingKey[Option[String]]("A comma-separated list of additional file extensions to be treated like a ZIP file, the contents will be extracted and analyzed.")
	lazy val dependencyCheckJarAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether Jar Analyzer will be used.")
	lazy val dependencyCheckDartAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the experimental Dart analyzer is enabled. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckKnownExploitedEnabled = settingKey[Option[Boolean]]("Sets whether the Known Exploited Vulnerability update and analyzer are enabled.")
	lazy val dependencyCheckKnownExploitedUrl = settingKey[Option[URL]]("Sets URL to the CISA Known Exploited Vulnerabilities JSON data feed.")
	lazy val dependencyCheckKnownExploitedValidForHours = settingKey[Option[Int]]("Set the interval in hours until the next check for CISA Known Exploited Vulnerabilities JSON data feed is performed.")
	lazy val dependencyCheckCentralAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether Central Analyzer will be used. If this analyzer is being disabled there is a good chance you also want to disable the Nexus Analyzer (see below).")
	lazy val dependencyCheckCentralAnalyzerUseCache = settingKey[Option[Boolean]]("Sets whether the Central Analyzer will cache results.")
	lazy val dependencyCheckOSSIndexAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the OSS Index Analyzer should be used.")
	lazy val dependencyCheckOSSIndexAnalyzerUrl = settingKey[Option[URL]]("Sets the URL to the OSS Index repository.")
	lazy val dependencyCheckOSSIndexAnalyzerUseCache = settingKey[Option[Boolean]]("Sets whether the OSS Index  Analyzer will cache results.")
	lazy val dependencyCheckOSSIndexWarnOnlyOnRemoteErrors = settingKey[Option[Boolean]]("Sets whether remote errors from the OSS Index (e.g. BAD GATEWAY, RATE LIMIT EXCEEDED) will result in warnings only instead of failing execution.")
	lazy val dependencyCheckOSSIndexAnalyzerUsername = settingKey[Option[String]]("The username to use for the Sonatype OSS Index service.")
	lazy val dependencyCheckOSSIndexAnalyzerPassword = settingKey[Option[String]]("The password to use for the Sonatype OSS Index service.")
	lazy val dependencyCheckNexusAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether Nexus Analyzer will be used. This analyzer is superseded by the Central Analyzer; however, you can configure this to run against a Nexus Pro installation.")
	lazy val dependencyCheckNexusUrl = settingKey[Option[URL]]("Defines the Nexus Server's web service end point (example http://domain.enterprise/service/local/). If not set the Nexus Analyzer will be disabled.")
	lazy val dependencyCheckNexusUsesProxy = settingKey[Option[Boolean]]("Whether or not the defined proxy should be used when connecting to Nexus.")
	lazy val dependencyCheckNexusUser = settingKey[Option[String]]("The username to authenticate to the Nexus Server's web service end point. If not set the Nexus Analyzer will use an unauthenticated connection.")
	lazy val dependencyCheckNexusPassword = settingKey[Option[String]]("The password to authenticate to the Nexus Server's web service end point. If not set the Nexus Analyzer will use an unauthenticated connection.")
	lazy val dependencyCheckPyDistributionAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the experimental Python Distribution Analyzer will be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckPyPackageAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the experimental Python Package Analyzer will be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckRubygemsAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the experimental Ruby Gemspec Analyzer will be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckOpensslAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the openssl Analyzer should be used.")
	lazy val dependencyCheckCmakeAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental CMake Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckAutoconfAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental autoconf Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckMavenInstallAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the Maven install Analyzer should be used.")
	lazy val dependencyCheckPipAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the experimental pip Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckPipfileAnalyzerEnabled = settingKey[Option[Boolean]](" Sets whether the experimental Pipfile Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckPoetryAnalyzerEnabled = settingKey[Option[Boolean]](" Sets whether or not the poetry Analyzer should be used.")
	lazy val dependencyCheckComposerAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental PHP Composer Lock File Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckCpanFileAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Perl CPAN File Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckNodeAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the retired Node.js Analyzer should be used.")
	lazy val dependencyCheckNodePackageSkipDevDependencies = settingKey[Option[Boolean]]("Sets whether the retired Node.js Analyzer will skip devDependencies.")
	lazy val dependencyCheckNodeAuditAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the Node Audit Analyzer should be used.")
	lazy val dependencyCheckNodeAuditAnalyzerUrl = settingKey[Option[URL]]("Sets the URL to the NPM Audit API. If not set uses default URL.")
	lazy val dependencyCheckNodeAuditSkipDevDependencies = settingKey[Option[Boolean]]("Sets whether the Node.js Audit Analyzer will skip devDependencies.")
	lazy val dependencyCheckNodeAuditAnalyzerUseCache = settingKey[Option[Boolean]]("Sets whether the Node Audit Analyzer will cache results.")
	lazy val dependencyCheckNPMCPEAnalyzerEnabled = settingKey[Option[Boolean]](" Sets whether the or not the experimental NPM CPE Analyzer should be used.")
	lazy val dependencyCheckYarnAuditAnalyzerEnabled = settingKey[Option[Boolean]](" Sets whether the Yarn Audit Analyzer should be used. This analyzer requires yarn and an internet connection. Use `dependencyCheckNodeAuditSkipDevDependencies` to skip dev dependencies.")
	lazy val dependencyCheckPathToYarn = settingKey[Option[File]]("Sets the path to the Yarn executable.")
	lazy val dependencyCheckPNPMAuditAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the Pnpm Audit Analyzer is enabled. This analyzer requires pnpm and an internet connection. Use `nodeAuditSkipDevDependencies` to skip dev dependencies.")
	lazy val dependencyCheckPathToPNPM = settingKey[Option[File]]("Sets the path to pnpm.")
	lazy val dependencyCheckNuspecAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the .NET Nuget Nuspec Analyzer will be used.")
	lazy val dependencyCheckNugetConfAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the experimental .NET Nuget packages.config Analyzer will be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckCocoapodsEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Cocoapods Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckMixAuditAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Elixir Mix Audit Analyzer should be used.")
	lazy val dependencyCheckMixAuditPath = settingKey[Option[File]]("Sets the path to the mix_audit executable; only used if Elixir Mix Audit Analyzer is enabled and experimental analyzers are enabled.")
	lazy val dependencyCheckSwiftEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Swift Package Manager Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckSwiftPackageResolvedAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Swift Package Resolved Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckBundleAuditEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Ruby Bundle Audit Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckPathToBundleAudit = settingKey[Option[File]]("The path to Ruby Bundle Audit.")
	lazy val dependencyCheckBundleAuditWorkingDirectory = settingKey[Option[File]]("Sets the path for the working directory that the Ruby Bundle Audit binary should be executed from.")
	lazy val dependencyCheckAssemblyAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the .NET Assembly Analyzer should be used.")
	lazy val dependencyCheckMSBuildAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether the MSBuild Analyzer should be used.")
	lazy val dependencyCheckPEAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental PE Analyzer that reads the PE headers of DLL and EXE files should be used.")
	lazy val dependencyCheckPathToDotNETCore = settingKey[Option[File]]("The path to Core for .NET assembly analysis on non-windows systems.")
	lazy val dependencyCheckRetireJSAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not the RetireJS Analyzer should be used.")
	lazy val dependencyCheckRetireJSForceUpdate = settingKey[Option[Boolean]]("Sets whether the RetireJS Analyzer should update regardless of the dependencyCheckAutoUpdate setting.")
	lazy val dependencyCheckRetireJSAnalyzerRepoJSUrl = settingKey[Option[URL]]("Sets the URL to the RetireJS repository. Note: the file name must be 'jsrepository.json'")
	lazy val dependencyCheckRetireJsAnalyzerRepoUser = settingKey[Option[String]]("Username for authentication to connect to RetireJS URL.")
	lazy val dependencyCheckRetireJsAnalyzerRepoPassword = settingKey[Option[String]]("Password for authentication to connect to RetireJS URL.")
	lazy val dependencyCheckRetireJsAnalyzerRepoValidFor = settingKey[Option[Int]]("Set the interval in hours until the next check for CVEs updates is performed by the RetireJS analyzer.")
	lazy val dependencyCheckRetireJsAnalyzerFilters = settingKey[Seq[String]]("Set one or more filters for the RetireJS analyzer. ")
	lazy val dependencyCheckRetireJsAnalyzerFilterNonVulnerable = settingKey[Option[Boolean]]("Sets whether or not the RetireJS analyzer should filter non-vulnerable dependencies.")
	lazy val dependencyCheckArtifactoryAnalyzerEnabled = settingKey[Option[Boolean]]("Sets whether or not teh JFrog Artifactory Analyzer will be used.")
	lazy val dependencyCheckArtifactoryAnalyzerUrl = settingKey[Option[URL]]("The Artifactory server URL.")
	lazy val dependencyCheckArtifactoryAnalyzerUseProxy = settingKey[Option[Boolean]]("Sets whether Artifactory should be accessed through a proxy or not.")
	lazy val dependencyCheckArtifactoryAnalyzerParallelAnalysis = settingKey[Option[Boolean]]("Sets whether the Artifactory analyzer should be run in parallel or not.")
	lazy val dependencyCheckArtifactoryAnalyzerUsername = settingKey[Option[String]]("The user name (only used with API token) to connect to Artifactory instance.")
	lazy val dependencyCheckArtifactoryAnalyzerApiToken = settingKey[Option[String]]("The API token to connect to Artifactory instance.")
	lazy val dependencyCheckArtifactoryAnalyzerBearerToken = settingKey[Option[String]]("he bearer token to connect to Artifactory instance.")
	lazy val dependencyCheckGolangDepEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Golang Dependency Analyzer should be used. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckGolangModEnabled = settingKey[Option[Boolean]]("Sets whether or not the experimental Golang Module Analyzer should be used. Requires `go` to be installed. dependencyCheckEnableExperimental must be set to true.")
	lazy val dependencyCheckPathToGo = settingKey[Option[File]]("The path to the \"go\" runtime.")

	// Advanced configuration
	lazy val dependencyCheckNvdApiKey = settingKey[Option[String]]("The API key used when connecting to the NVD API.")
	lazy val dependencyCheckNvdApiUser = settingKey[Option[String]]("The username used when connecting to the NVD API.")
	lazy val dependencyCheckNvdApiPassword = settingKey[Option[String]]("The password used when connecting to the NVD API.")
	lazy val dependencyCheckNvdApiStartYear = settingKey[Option[Int]]("The first year of NVD CVE data to download from the NVD API.")
	lazy val dependencyCheckConnectionTimeout = settingKey[Option[Int]]("Sets the URL Connection Timeout (in milliseconds) used when downloading external data. ")
	lazy val dependencyCheckConnectionReadTimeout = settingKey[Option[Int]]("Sets the URL Connection Read Timeout (in milliseconds) used when downloading external data. ")
	lazy val dependencyCheckDataDirectory = settingKey[Option[File]]("Sets the data directory to hold SQL CVEs contents. This should generally not be changed. ")
	lazy val dependencyCheckDatabaseDriverName = settingKey[Option[String]]("The name of the database driver. Example: org.h2.Driver. ")
	lazy val dependencyCheckDatabaseDriverPath = settingKey[Option[File]]("The path to the database driver JAR file; only used if the driver is not in the class path. ")
	lazy val dependencyCheckConnectionString = settingKey[Option[String]]("The connection string used to connect to the database. ")
	lazy val dependencyCheckDatabaseUser = settingKey[Option[String]]("The username used when connecting to the database. ")
	lazy val dependencyCheckDatabasePassword = settingKey[Option[String]]("The password used when connecting to the database. ")
	lazy val dependencyCheckHostedSuppressionsForceUpdate = settingKey[Option[Boolean]]("Whether the hosted suppressions file will update regardless of the `dependencyCheckAutoUpdate` setting.")
	lazy val dependencyCheckHostedSuppressionsEnabled = settingKey[Option[Boolean]]("Whether the hosted suppression file will be used.")
	lazy val dependencyCheckHostedSuppressionsUrl = settingKey[Option[URL]]("The URL to a mirrored copy of the hosted suppressions file for internet-constrained environments.")
	lazy val dependencyCheckHostedSuppressionsValidForHours = settingKey[Option[Int]]("Sets the number of hours to wait before checking for new updates from the NVD.")

	// TaskKeys
	lazy val dependencyCheck = TaskKey[Unit]("dependencyCheck", "Runs dependency-check against the project and generates a report per sub project.")
	lazy val dependencyCheckAggregate = TaskKey[Unit]("dependencyCheckAggregate", "Runs dependency-check against project aggregates and combines the results into a single report.")
	lazy val dependencyCheckAnyProject = TaskKey[Unit]("dependencyCheckAnyProject", "Runs dependency-check against all projects and combines the results into a single report.")
	lazy val dependencyCheckUpdateOnly = TaskKey[Unit]("dependencyCheckUpdateOnly", "Updates the local cache of the NVD data from NIST.")
	lazy val dependencyCheckPurge = TaskKey[Unit]("dependencyCheckPurge", "Deletes the local copy of the NVD. This is used to force a refresh of the data.")
	lazy val dependencyCheckListSettings = TaskKey[Unit]("dependencyCheckListSettings", "List the settings of the plugin")
}
