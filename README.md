# Kafka Cluster Setup Guide

## Basic Operations

### Cluster Management

```bash
# Start the cluster
docker-compose -f docker-compose-dev-setup.yml --env-file docker-compose-dev-setup.env up -d

# Stop and remove the cluster
docker-compose -f docker-compose-dev-setup.yml --env-file docker-compose-dev-setup.env down

# Pause the cluster services
docker-compose -f docker-compose-dev-setup.yml --env-file docker-compose-dev-setup.env pause

# Resume the cluster services
docker-compose -f docker-compose-dev-setup.yml --env-file docker-compose-dev-setup.env unpause

# Clean up everything (including volumes, networks)
docker-compose -f docker-compose-dev-setup.yml --env-file docker-compose-dev-setup.env down -v
```