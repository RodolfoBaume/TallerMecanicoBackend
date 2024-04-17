package com.tallerMecanico;

import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.tallerMecanico.entity.Empleado;
import com.tallerMecanico.entity.Rol;
import com.tallerMecanico.entity.Usuario;
import com.tallerMecanico.repository.IEmpleadoRepository;
import com.tallerMecanico.repository.IRolRepository;
import com.tallerMecanico.repository.IUsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner{

	private IUsuarioRepository usuarioRepository;
	private IEmpleadoRepository empleadoRepository;
	private IRolRepository rolRepository;
	
	public DataLoader(IUsuarioRepository usuarioRepository, IEmpleadoRepository empleadoRepository, IRolRepository rolRepository) {
		this.usuarioRepository= usuarioRepository;
		this.empleadoRepository = empleadoRepository;
		this.rolRepository = rolRepository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		// solo ejecutar si no hay empleados registrados
		
		// Verificar si la tabla de empleados está vacía
	    long count = empleadoRepository.count();
		
	    if (count == 0) {
	    	
	    	//Crear 2 usuarios ADMIN
	    	
	    	// Crear y guardar el primer usuario y asociarle el rol "ADMIN"
		    Usuario usuario1 = new Usuario();
		    usuario1.setEmail("correo1@example.com");
		    usuario1.setPassword("contraseña1");

		    // Buscar el rol "ADMIN" en la base de datos
		    Optional<Rol> adminRoleOptional = rolRepository.findByNombre("ADMIN");

		    // Verificar si se encontró el rol
		    Rol adminRole;
		    if (adminRoleOptional.isPresent()) {
		        // Obtener el rol del Optional
		        adminRole = adminRoleOptional.get();
		    } else {
		        // Si no se encuentra, crear un nuevo rol "ADMIN" y guardarlo
		        adminRole = new Rol();
		        adminRole.setNombre("ADMIN");
		        // Guardar el nuevo rol en la base de datos
		        adminRole = rolRepository.save(adminRole);
		    }

		    usuario1.getRol().add(adminRole);
		    usuarioRepository.save(usuario1);

		    // Crear y guardar el primer empleado asociado al primer usuario
		    Empleado empleado1 = new Empleado();
		    empleado1.setNombre("Nombre1");
		    empleado1.setApellidoPaterno("Apellido Paterno1");
		    empleado1.setApellidoMaterno("Apellido Materno1");
		    empleado1.setNss(1234567890); // Por ejemplo
		    empleado1.setCurp("CURP1");
		    empleado1.setRfc("RFC1");
		    empleado1.setPuesto("Puesto1");
		    empleado1.setObservaciones("Observaciones1");

		    empleado1.setUsuario(usuario1);
		    empleadoRepository.save(empleado1);

		    // Crear y guardar el segundo usuario y asociarle el rol "ADMIN"
		    Usuario usuario2 = new Usuario();
		    usuario2.setEmail("correo2@example.com");
		    usuario2.setPassword("contraseña2");

		    usuario2.getRol().add(adminRole); // Reutilizamos el mismo rol "ADMIN"

		    usuarioRepository.save(usuario2);

		    // Crear y guardar el segundo empleado asociado al segundo usuario
		    Empleado empleado2 = new Empleado();
		    empleado2.setNombre("Nombre2");
		    empleado2.setApellidoPaterno("Apellido Paterno2");
		    empleado2.setApellidoMaterno("Apellido Materno2");
		    empleado2.setNss(1234567891); // Por ejemplo
		    empleado2.setCurp("CURP2");
		    empleado2.setRfc("RFC2");
		    empleado2.setPuesto("Puesto2");
		    empleado2.setObservaciones("Observaciones2");

		    empleado2.setUsuario(usuario2);
		    empleadoRepository.save(empleado2);
	    } else {
	        // La tabla de empleados no está vacía, no es necesario ejecutar la lógica
	        System.out.println("La tabla de empleados no está vacía. No se ejecutará la lógica para crear usuarios y empleados.");
	    }
	    
		
	}

	
}