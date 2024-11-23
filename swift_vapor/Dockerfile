FROM swift:6.0

WORKDIR /app

# Install Vapor dependencies
RUN apt-get update && apt-get install -y \
    libssl-dev \
    libsqlite3-dev \
    libz-dev

# Copy the package manifest and resolve dependencies
COPY ./Package.swift ./
RUN swift package resolve

# Copy the entire project and build it
COPY . ./
RUN swift build -c release

# Expose port 3000
EXPOSE 3000

# Run the application
CMD [".build/release/App"]
