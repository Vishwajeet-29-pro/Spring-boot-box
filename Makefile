# Variables
GRADLE=./gradlew
EXCLUDED_DIRS=(build src .gradle .git)  # Directories to exclude
MODULES=$(shell ls -d * | grep app)

# Commands
.PHONY: all clean build test run help

# Default: Build and test all modules
all: build

# Clean the project
clean:
	$(GRADLE) clean
	@echo "Cleaned all build files."

# Runs the same thing as the pipeline.
.PHONY: pipeline
pipeline: all

# Build the entire project
build:
	$(GRADLE) build $(foreach MODULE,$(MODULES),:$(MODULE):build)
	@echo "Built all modules: $(MODULES)"

# Test the entire project
test:
	$(GRADLE) test
	@echo "Tested the entire project."


# Run the root Spring Boot application (if applicable)
run:
	$(GRADLE) bootRun
	@echo "Running the root Spring Boot application."

# Run individual modules
run-h2:
	$(GRADLE) :app-db-h2-connection:bootRun
	@echo "Running db-h2-connection module."

run-postgres:
	$(GRADLE) :app-db-postgres-connection:bootRun
	@echo "Running db-postgres-connection module."

run-mysql:
	$(GRADLE) :app-db-mysql-connection:bootRun
	@echo "Running db-mysql-connection module."

run-mongodb:
	$(GRADLE) :app-db-mongo-connection:bootRun
	@echo "Running db-mongo-connection module."

run-spring-security-1.0:
	$(GRADLE) :app-spring-security-basic:bootRun
	@echo "Running spring security basic module."

run-reactive-webflux-basic:
	$(GRADLE) :app-reactive-webflux-basic:bootRun
	@echo "Running spring reactive webflux basic module"

# Build and test a specific module
run-module:
	@read -p "Enter the module name (e.g., db-h2-connection): " MODULE && \
	$(GRADLE) :$$MODULE:clean :$$MODULE:build :$$MODULE:test && \
	echo "Built and tested $$MODULE."

# Help menu
help:
	@echo "Usage:"
	@echo "  make all         - Build and test all modules"
	@echo "  make clean       - Clean all build files"
	@echo "  make build       - Build all modules"
	@echo "  make test        - Test all modules"
	@echo "  make run         - Run the root application"
	@echo "  make run-module  - Build and test a specific module"
	@echo "  make help        - Show this help message"

-include .User.mk