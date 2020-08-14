@RestController
class PingController {

    @GetMapping('/ping')
    String ping() {
        return 'pong\n'
    }
}
