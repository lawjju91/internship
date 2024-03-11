    package com.test3.dfs_3.Service;

    import com.test3.dfs_3.entity.Demand;
    import com.test3.dfs_3.entity.Member;
    import com.test3.dfs_3.repository.DemandRepository;
    import com.test3.dfs_3.repository.MemberRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Map;
    import java.util.Optional;

    @Service
    public class DemandService {
        private final DemandRepository demandRepository;
        private final MemberRepository memberRepository;

        @Autowired
        public DemandService(DemandRepository demandRepository, MemberRepository memberRepository) {
            this.demandRepository = demandRepository;
            this.memberRepository = memberRepository;
        }

        public void fulfillDemand(Long demandId) {
            Optional<Demand> demandOptional = demandRepository.findById(demandId);
            if (demandOptional.isPresent()) {
                Demand demand = demandOptional.get();
                List<Member> availableMembers = memberRepository.findByStatus("Available");

                // Find a member with matching skills and assign to the demand
                Member preferredMember = findPreferredMember(demand, availableMembers);
                if (preferredMember != null) {
                    preferredMember.setStatus("Assigned");
                    demand.setStatus("Closed");
                    demandRepository.save(demand);
                    memberRepository.save(preferredMember);
                    System.out.println("Demand fulfilled successfully.");
                } else {
                    System.out.println("No matching member found to fulfill the demand.");
                }
            } else {
                System.out.println("Demand not found with ID: " + demandId);
            }
        }

        private Member findPreferredMember(Demand demand, List<Member> availableMembers) {
            for (Member member : availableMembers) {
                if (isMemberSuitableForDemand(member, demand)) {
                    return member;
                }
            }
            return null;
        }

        private boolean isMemberSuitableForDemand(Member member, Demand demand) {
            if (!member.getLevel().equals(demand.getLevel())) {
                return false;
            }

            // Check if member's skills match with demand's skills
            for (Map.Entry<String, Integer> entry : demand.getSkills().entrySet()) {
                String skill = entry.getKey();
                int requiredExperience = entry.getValue();
                if (!member.getSkills().containsKey(skill) || member.getSkills().get(skill) < requiredExperience) {
                    return false;
                }
            }
            return true;
        }
    }
