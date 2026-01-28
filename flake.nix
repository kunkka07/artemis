{
  description = "Artemis development environment";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };

  outputs =
    { nixpkgs, ... }:
    let
      systems = [
        "x86_64-linux"
        "aarch64-linux"
        "x86_64-darwin"
        "aarch64-darwin"
      ];
      forAllSystems = nixpkgs.lib.genAttrs systems;
    in
    {
      devShells = forAllSystems (
        system:
        let
          pkgs = import nixpkgs {
            inherit system;
            config.allowUnfree = true;
          };
        in
        {
          default = pkgs.mkShell {
            packages = with pkgs; [
              gradle_9
              graalvmPackages.graalvm-oracle
            ];
            shellHook = ''
              echo "Dev environment ready for ${system}"
              echo "Building project with graalvm"
              cd cli
              ./gradlew nativeCompile --no-configuration-cache
              echo "Finished building project with graalvm, go to the build dir"
              cd app/build/native/nativeCompile
              chmod +x app
              echo "You can run the app now"
            '';
          };
        }
      );
    };
}
