class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/" {
            controller = 'user'
            action = 'index'
        }
//		"/"(view:"/index")
		"500"(view:'/error')
	}
}
